<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main" />
    <g:set var="entityName" value="${message(code: 'annonce.label', default: 'Annonce')}" />
    <title><g:message code="default.list.label" args="[entityName]" /></title>
</head>
<body>
<a href="#list-annonce" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
<div class="nav" role="navigation">
    <ul>
        <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
        <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
    </ul>
</div>
<div id="list-annonce" class="content scaffold-list" role="main">
    <h1><g:message code="default.list.label" args="[entityName]" /></h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
%{--            <f:table collection="${annonceList}" />--}%

    <table>
        <thead>
        <tr>

            <th class="sortable" ><a href="/annonce/index?sort=title&amp;max=10&amp;order=asc">Title</a></th>

            <th class="sortable" ><a href="/annonce/index?sort=description&amp;max=10&amp;order=asc">Description</a></th>

            <th class="sortable" ><a href="/annonce/index?sort=validTill&amp;max=10&amp;order=asc">Valid Till</a></th>

            <th class="sortable" ><a href="/annonce/index?sort=illustrations&amp;max=10&amp;order=asc">Illustrations</a></th>

            <th class="sortable" ><a href="/annonce/index?sort=state&amp;max=10&amp;order=asc">State</a></th>

            <th class="sortable" ><a href="/annonce/index?sort=author&amp;max=10&amp;order=asc">Author</a></th>

        </tr>
        </thead>

        <g:each in="${annonceList}" var="instance">
            <tr>
                <td><g:link controller="annonce" action="show" id="${instance.id}">${instance.title}</g:link> </td>
                <td>${instance.description} </td>
                <td>${instance.validTill} </td>
                <td>
                    <ul>
                        <g:each in="${instance.illustrations}" var="illustrations">
                            <li>
                                <img width="121" height="121" src="http://localhost:8091/assets/${illustrations.filename}"/>
                            </li>
                        </g:each>
                    </ul>
                </td>
                <td>${instance.state} </td>
                <td>
                    <ul>
                        <g:each in="${instance.author}" var="author">
                            <li>
                                <g:link controller="user" action="show" id="${author.id}">${author.username}</g:link></li>
                            </li>
                        </g:each>
                    </ul>
                </td>
            </tr>

        </g:each>
    </table>

    <div class="pagination">
        <g:paginate total="${annonceCount ?: 0}" />
    </div>
</div>
</body>
</html>