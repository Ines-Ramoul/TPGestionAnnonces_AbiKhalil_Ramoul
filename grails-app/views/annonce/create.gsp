<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'annonce.label', default: 'Annonce')}"/>
    <title><g:message code="default.create.label" args="[entityName]"/></title>
</head>

<body>
<a href="#create-annonce" class="skip" tabindex="-1"><g:message code="default.link.skip.label"
                                                                default="Skip to content&hellip;"/></a>

<div class="nav" role="navigation">
    <ul>
        <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
        <li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]"/></g:link></li>
    </ul>
</div>

<div id="create-annonce" class="content scaffold-create" role="main">
    <h1><g:message code="default.create.label" args="[entityName]"/></h1>
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
    <g:form resource="${this.annonce}" method="POST" enctype="multipart/form-data">
        <fieldset class="form">
            <f:field bean="annonce" property="title">
                <f:input bean="annonce" property="title"/>
            </f:field>

            <f:field bean="annonce" property="description">
                <f:input bean="annonce" property="description"/>
            </f:field>

            <f:field bean="annonce" property="validTill">
                <f:input bean="annonce" property="validTill"/>
            </f:field>
            <div class='fieldcontain'>
            <label >Upload Image</label>
                    <input type="file" name="myFile" />
            </div>
        <f:field bean="annonce" property="state">
            <f:input bean="annonce" property="state"/>
        </f:field>
        <f:field bean="annonce" property="author">
            <f:input bean="annonce" property="author"/>
        </f:field>
</div>
        <fieldset class="buttons">
        <g:submitButton name="create" class="save"
                        value="${message(code: 'default.button.create.label', default: 'Create')}"/>
        </fieldset>
    </g:form>
</div>
</body>
</html>

