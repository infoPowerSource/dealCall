<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var = "CTX" value="${pageContext.request.contextPath}" />
<c:set var = "JS_PATH" value="${CTX}/static/js" />
<c:set var = "CSS_PATH" value="${CTX}/static/css" />
<c:set var = "IMAGE_PATH" value="${CTX}/static/images" />
<c:set var = "SOUND_PATH" value="${CTX}/static/sound" />


<script type="text/javascript">
    var BASE_PATH = '${CTX}';
    var SOUND_PATH = '${SOUND_PATH}';
</script>