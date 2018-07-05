<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="modal fade" id="modalDelete" tabindex="-1" role="dialog">
	<div class="modal-dialog modal-danger" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title">삭제</h4>
			</div>
			<div class="modal-body">
				<form id="deleteForm" role="form">
					<div class="box-body">
						<p>선택한 항목을 삭제 합니까?</p>
						<input type="hidden" id="deleteIdOne">
					</div>
				</form>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-outline pull-left" data-dismiss="modal">취소</button>
				<button type="button" class="btn btn-outline" onclick="javascript:del();">삭제</button>
			</div>
		</div>
	</div>
</div>

<div class="modal fade" id="modalSuccess" tabindex="-1" role="dialog">
	<div class="modal-dialog modal-success" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title">처리 결과</h4>
			</div>
			<div class="modal-body">
				<div class="box-body">
					<p>정상 처리 완료</p>
				</div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-outline" data-dismiss="modal">확인</button>
			</div>
		</div>
	</div>
</div>


 <footer class="main-footer">
   <div class="pull-right hidden-xs">
     <b>Version</b> 0.1.0
   </div>
   <strong>Copyright &copy; 2018 -  <a href="http://www.ksign.com">Ksign</a>.</strong> All rights
   reserved.
 </footer>