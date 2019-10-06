<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'annonce.label', default: 'Annonce')}"/>
    <title><g:message code="default.edit.label" args="[entityName]"/></title>
</head>

<body>
<a href="#edit-annonce" class="skip" tabindex="-1"><g:message code="default.link.skip.label"
                                                              default="Skip to content&hellip;"/></a>

<div class="nav" role="navigation">
    <ul>
        <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
        <li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]"/></g:link></li>
        <li><g:link class="create" action="create"><g:message code="default.new.label"
                                                              args="[entityName]"/></g:link></li>
    </ul>
</div>

<div id="edit-annonce" class="content scaffold-edit" role="main">
    <h1><g:message code="default.edit.label" args="[entityName]"/></h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <g:hasErrors bean="${this.annonce}">
        <ul class="errors" role="alert">
            <g:eachError bean="${this.annonce}" var="error">
                <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message
                        error="${error}"/></li>
            </g:eachError>
        </ul>
    </g:hasErrors>
%{--    <g:form resource="${this.annonce}" method="PUT">--}%
    <form action="/annonce/update/${annonce.id}" method="POST" enctype="multipart/form-data">
        <g:hiddenField name="version" value="${this.annonce?.version}"/>
        <fieldset class="form">
            %{--<f:all bean="annonce"/>--}%
            <f:field bean="annonce" property="title">
                <f:input bean="annonce" property="title"/>
            </f:field>

            <f:field bean="annonce" property="description">
                <f:input bean="annonce" property="description"/>
            </f:field>

            <f:field bean="annonce" property="validTill">
                <f:input bean="annonce" property="validTill"/>
            </f:field>

            <div class="fieldcontain">
                <label>Upload Image</label>
                <input type="file" name="myFile"/>
                <span id="illustrations-label" class="property-label">Illustrations</span>

                <div class="property-value" aria-labelledby="illustrations-label">
                    <ul>

                        <g:each in="${annonce.illustrations}" var="illustration">

                            <li>
                                <a href="/illustration/show/${illustration.id}">
                                    <img width="121" height="121"
                                         src="http://localhost:8091/assets/${illustration.filename}"/>
                                </a>
                                <g:link controller="annonce" action="deleteIllustration"
                                        params="[annonceId: annonce.id, illustrationId: illustration.id]">DELETE</g:link>

                            </li>
                        </g:each>

                    </ul>
                </div>
            </div>

            <f:field bean="annonce" property="state">
                <f:input bean="annonce" property="state"/>
            </f:field>
            <f:field bean="annonce" property="author">
                <f:input bean="annonce" property="author"/>
            </f:field>

        </fieldset>
        <fieldset class="buttons">
            <input class="save" type="submit"
                   value="${message(code: 'default.button.update.label', default: 'Update')}"/>
        </fieldset>
    </form>
    %{--</g:form>--}%
</div>
</body>
</html>
