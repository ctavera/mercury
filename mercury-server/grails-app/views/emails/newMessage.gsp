<%-- User: cesarreyes, Date: 04/07/11, Time: 23:50 --%><%@ page contentType="text/html;charset=UTF-8" %>Se ha creado un mensaje nuevo en Mercury, creado por ${message.user.fullName}

Titulo:
${message.title}

Puedes ver el mensaje completo en:
${grailsApplication.config.grails.serverURL}/messages/view/${message.id}



Este mail es generado automaticamente por Mercury.