<%--
  User: cesarreyes
  Date: 28/11/10
  Time: 23:48
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
  <title>Edit user</title>
  <meta content="main" name="layout"/>
</head>
<body>

<content tag="navbar">
  <g:render template="/shared/menu" model="[selected:'main']"/>
</content>

<div id="main">
  <div class="block" id="block-text">

    <div class="secondary-navigation">
      <ul class="wat-cf">
        <li class="first active"><g:link action="editUser" id="${user.id}">Editar</g:link></li>
        <li><g:link action="editRoles" id="${user.id}">Permisos</g:link></li>
      </ul>
    </div>

    <div class="content">
      <h2 class="title">Editar usuario</h2>
      <div class="inner">
        <g:form action="updateUser" class="form">
          <g:hiddenField name="id" value="${user.id}"/>

          <div class="group">
            <label class="label">Usuario</label>
            <g:textField name="username" value="${user.username}"/>
          </div>
          <div class="group">
            <label class="label">Nombre</label>
            <g:textField name="firstName" value="${user.firstName}"/>
          </div>
          <div class="group">
            <label class="label">Apellido</label>
            <g:textField name="lastName" value="${user.lastName}"/>
          </div>
          <div class="group">
            <label class="label">Email</label>
            <g:textField name="email" value="${user.email}"/>
          </div>
          <div class="group">
            <label class="label">Activo</label>
            <g:checkBox name="enabled" value="${user.enabled}"/>
          </div>
          <div class="group navform wat-cf">
            <button class="button" type="submit">
              <img src="${resource(dir:'images/icons', file:'tick.png')}" alt="Guardar" /> Guardar
            </button>
            <g:link action="index" class="button"><img src="${resource(dir:'images/icons', file:'cross.png')}" alt="Cancel"/> Cancel</g:link>
          </div>
        </g:form>
      </div>
    </div>
  </div>
</div>

</body>
</html>