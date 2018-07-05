<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!-- jQuery 2.2.3 -->
<script src="${ctxPath}/resources/js/plugins/jQuery/jquery-2.2.3.min.js"></script>
<!-- Bootstrap 3.3.6 -->
<script src="${ctxPath}/resources/bootstrap/js/bootstrap.min.js"></script>
<script src="${ctxPath}/resources/js/plugins/jQuery/jquery-migrate-1.4.1.js"></script>

<!-- FastClick -->
<script src="${ctxPath}/resources/js/plugins/fastclick/fastclick.js"></script>
<!-- AdminLTE App -->
<script src="${ctxPath}/resources/bootstrap/js/app.min.js"></script>
<!-- Sparkline -->
<script src="${ctxPath}/resources/js/plugins/sparkline/jquery.sparkline.min.js"></script>
<!-- jvectormap -->
<script src="${ctxPath}/resources/js/plugins/jvectormap/jquery-jvectormap-1.2.2.min.js"></script>
<script src="${ctxPath}/resources/js/plugins/jvectormap/jquery-jvectormap-world-mill-en.js"></script>
<!-- SlimScroll 1.3.0 -->
<script src="${ctxPath}/resources/js/plugins/slimScroll/jquery.slimscroll.min.js"></script>
<!-- ChartJS 1.0.1 -->

<!-- iCheck -->
<script src="${ctxPath}/resources/js/plugins/iCheck/icheck.min.js"></script>
<script src="${ctxPath}/resources/js/ajax-api-base-2.0.1.js"></script>
<!-- <script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.18.1/moment-with-locales.js"></script> -->
<script src="${ctxPath}/resources/js/plugins/moment/moment-with-locales.min.js"></script>
<!-- DataTables -->
<script src="${ctxPath}/resources/js/plugins/datatables/jquery.dataTables.min.js"></script>
<script src="${ctxPath}/resources/js/plugins/datatables/dataTables.bootstrap.min.js"></script>
<script src="${ctxPath}/resources/js/plugins/datatables/extensions/Responsive/js/dataTables.responsive.js"></script>

<script>
$.fn.dataTable.render.ellipsis = function ( cutoff, wordbreak, escapeHtml ) {
    var esc = function ( t ) {
        return t
            .replace( /&/g, '&amp;' )
            .replace( /</g, '&lt;' )
            .replace( />/g, '&gt;' )
            .replace( /"/g, '&quot;' );
    };
 
    return function ( d, type, row ) {
        // Order, search and type get the original data
        if ( type !== 'display' ) {
            return d;
        }
 
        if ( typeof d !== 'number' && typeof d !== 'string' ) {
            return d;
        }
 
        d = d.toString(); // cast numbers
 
        if ( d.length < cutoff ) {
            return d;
        }
 
        var shortened = d.substr(0, cutoff-1);
 
        // Find the last white space character in the string
        if ( wordbreak ) {
            shortened = shortened.replace(/\s([^\s]*)$/, '');
        }
 
        // Protect against uncontrolled HTML input
        if ( escapeHtml ) {
            shortened = esc( shortened );
        }
 
        return '<span class="ellipsis" title="'+esc(d)+'">'+shortened+'&#8230;</span>';
    };
};

function delModal(id) {
	$('#deleteIdOne').val(id);//footer.jsp
	$('#modalDelete').modal({backdrop: 'static', keyboard: false});
}

function listDraw(){
	$('#listTable').DataTable().draw();
}

function toStatusStr(status) {
	if(status == 0) {
		return '없음';
	} else if(status == 1) {
		return '사용';
	} else if(status == 2) {
		return '정지';
	} else {
		return 'Unknown';
	}
}


</script>