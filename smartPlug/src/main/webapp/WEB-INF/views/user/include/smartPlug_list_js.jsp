<%@page import="com.office.smartPlug.user.member.UserMemberVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script type="text/javascript">

<%
	UserMemberVo logined_userMemberVo = 
				(UserMemberVo) session.getAttribute("logined_userMemberVo");
%>

var days_kr = ['일', '월', '화', '수', '목', '금', '토'];

$(document).ready(function(){
	console.log('DOCUMENT READY!!');
	
	init_year_month_date();
	
	ajax_getSmartPlugItems();
	
	/* setDocumentHeight(); */
	
	init_main_event();
	
});

function setSelectDateOptions() {
	console.log('setSelectDateOptions() CALLED!!');
	
	let last = new Date(parseInt(getFull_year()), parseInt(getMonth()), 0);
	
	/* UI */
	$('select[name="smartPlug_list_date_select"]').children().remove();
	$('select[name="smartPlug_list_date_select"]').append('<option value="-1">-- 선택 --</option>');
	for (let i = 1; i <= last.getDate(); i++) {
		$('select[name="smartPlug_list_date_select"]').append('<option value="' + i + '">' + i + '</option>');
	}
	
}

function init_year_month_date() {
	console.log('init_year_month_date() CALLED!!');
	
	$('select[name="smartPlug_list_year_select"]').val(getFull_year()).prop('selected', true);
	$('select[name="smartPlug_list_month_select"]').val(getMonth()).prop('selected', true);
	
	setSelectDateOptions();
	$('select[name="smartPlug_list_date_select"]').val(getDate()).prop('selected', true);
	
}

function init_main_event() {
	console.log('init_main_event CALLED!!');
	
	// 페이지 번호 클릭 시
	$(document).on('click', '#section_wrap > div.page_wrap > div.page_number a', function() {
		console.log('page_number CLICK HANDLER!!');
		
		let pageNum = $(this).data('pagenum');
		setPageNum(pageNum);
		
		ajax_getSmartPlugItems();
		
	});
	
	// 페이지 번호 입력 시
	$(document).on('click', '#section_wrap > div.page_wrap > div.page_goto a.goto_page_btn', function() {
		console.log('page_goto BUTTON CLICK HANDLER!!');
		
		let targetPageNum = $('#section_wrap > div.page_wrap > div.page_goto input[name="page_target_num"]').val();
		let totalPageNum = $('#section_wrap > div.page_wrap > div.page_goto span.page_total_num').text();
		
		if (targetPageNum == '' || targetPageNum == ' ' || targetPageNum == null) {
			alert('이동하고자 하는 페이지 숫자를 입력하세요!!');
			$('#section_wrap > div.page_wrap > div.page_goto input[name="page_target_num"]').focus();
			return;
			
		} else if (parseInt(targetPageNum) < 1) {
			alert('1페이지 미만은 존재하지 않습니다!!');
			$('#section_wrap > div.page_wrap > div.page_goto input[name="page_target_num"]').val('');
			$('#section_wrap > div.page_wrap > div.page_goto input[name="page_target_num"]').focus();
			return;
			
		} else if (parseInt(targetPageNum) > parseInt(totalPageNum.replaceAll(',', ''))) {
			alert(totalPageNum + '을(를) 초과 할수 없습니다!!');
			$('#section_wrap > div.page_wrap > div.page_goto input[name="page_target_num"]').val('');
			$('#section_wrap > div.page_wrap > div.page_goto input[name="page_target_num"]').focus();
			return;
			
		}
		
		setPageNum(targetPageNum);
		
		ajax_getSmartPlugItems();
		
		$('#section_wrap > div.page_wrap > div.page_goto input[name="page_target_num"]').val('');
		
	});
	
	
	// 년도 설정
	$(document).on('change', 'select[name="smartPlug_list_year_select"]', function() {
		console.log('year_select CHANGE HANDLER!!');
		
		/* UI */
		$('select[name="smartPlug_list_month_select"]').val(-1).prop('selected', true);
		
		$('select[name="smartPlug_list_date_select"]').children().remove();
		$('select[name="smartPlug_list_date_select"]').append('<option value="-1">-- 선택 --</option>');
		$('select[name="smartPlug_list_date_select"]').val(-1).prop('selected', true);
		
		/* DATA */
		setFull_year($('select[name="smartPlug_list_year_select"]').val());
		setMonth($('select[name="smartPlug_list_month_select"]').val());
		setDate($('select[name="smartPlug_list_date_select"]').val());
		
		/* get items */
		ajax_getSmartPlugItems();
		
	});
	
	// 월 설정
	$(document).on('change', 'select[name="smartPlug_list_month_select"]', function() {
		console.log('month_select CHANGE HANDLER!!');

		/* UI */
		$('select[name="smartPlug_list_date_select"]').val(-1).prop('selected', true);
		
		/* DATA */
		setMonth($('select[name="smartPlug_list_month_select"]').val());
		setDate($('select[name="smartPlug_list_date_select"]').val());
		
		setSelectDateOptions();
		
		/* get items */
		ajax_getsmartPlugItems();
		
	});
	
	// 일 설정
	$(document).on('change', 'select[name="smartPlug_list_date_select"]', function() {
		console.log('date_select CHANGE HANDLER!!');
		
		/* DATA */
		setDate($('select[name="smartPlug_list_date_select"]').val());
		
		/* get items */
		ajax_getsmartPlugItems();
		
	});
	
}

function ajax_getSmartPlugItems() {
	console.log('ajax_getSmartPlugItems() CALLED!!');
	
	var msg = {
		't_id'		: '${logined_userMemberVo.getT_id()}',
		'pageNum'	: getPageNum(),
		'amount'	: getAmount(),
		'full_year'	: getFull_year(), 
		'month'		: getMonth(), 
		'date'		: getDate()
	};
	
	$.ajax({
		url: '<c:url value="/user/smartPlug/getSmartPlugItems" />', 
		type: 'POST', 
		data: JSON.stringify(msg), 
		contentType: 'application/json; charset=utf-8;', 
		dataType: 'json',  
		success: function(data) {
			console.log('AJAX SUCCESS - ajax_getSmartPlugItems()');
			
			$('#section_wrap .smartPlug_list table tbody').remove();
			
			let userSmartPlugVos = data.userSmartPlugVos;
			let pageMakerVo = data.pageMakerVo;
			
			console.log(userSmartPlugVos);
			console.log(pageMakerVo);
			
			for (let i = 0; i < userSmartPlugVos.length; i++) {
				
				let userSmartPlugVo = userSmartPlugVos[i];
				let d_no = userSmartPlugVo.d_no;
				let t_id = userSmartPlugVo.t_id;
				let d_status = userSmartPlugVo.d_status;
				let d_reg_date = userSmartPlugVo.d_reg_date;
				let d_mod_date = userSmartPlugVo.d_mod_date;
				
				console.log("d_no: " + d_no);
				console.log("t_id: " + t_id);
				
				let t = document.querySelector('#smartPlug_list_item');
				let clone = document.importNode(t.content, true);
				
				let clone_table = clone.querySelector("tbody");
				$(clone_table).attr('id', d_no);
				
				$('#section_wrap div.smartPlug_list table').append(clone);
				
				/* UI */
				
				$('#' + d_no + ' td.d_no').text(d_no);
				$('#' + d_no + ' td.t_id').text(t_id);
				$('#' + d_no + ' td.d_statuts').text(d_status);
				$('#' + d_no + ' td.d_reg_date').text(d_reg_date);
				$('#' + d_no + ' td.d_mod_date').text(d_mod_date);
				/* $('#' + d_no + ' td.txt a').text(d_txt);
				$('#' + d_no + ' td.thumnail img').attr('src', '<c:url value="/diaryUploadImg/"/>' + t_id + '/' + d_img_name);
				$('#' + d_no + ' td.thumnail img').attr('src', '<c:url value="/diaryUploadImg/${logined_userMemberVo.getT_id()}/"/>' + d_img_name); */
				
				/* DATA */
				$('#' + d_no + ' td.txt a').data('d_no', d_no);
				$('#' + d_no + ' td.thumnail a').data('d_no', d_no);
				$('#' + d_no + ' td.modify_delete a').data('d_no', d_no);
				
				/* PAGE UI */
				$('#section_wrap > div.page_wrap > div.page_number').children().remove();
				
				// 이전 페이지
				if (pageMakerVo.prev) {
					$('#section_wrap > div.page_wrap > div.page_number').append('<a href="#none" data-pagenum="' + (pageMakerVo.startPage - 1) + '">PRE</a>');
				}
				
				// 처음 페이지
				if (getPageNum() > 10) {
					$('#section_wrap > div.page_wrap > div.page_number').append('<a href="#none" data-pagenum="1">1</a><span>...</span>');
				}
				
				// 페이지 넘버링
				for (let p = parseInt(pageMakerVo.startPage); p <= parseInt(pageMakerVo.endPage); p++) {
					$('#section_wrap > div.page_wrap > div.page_number').append('<a href="#none" data-pagenum="' + p + '">' + p + '</a>');
				}
				// 페이지 하이라이트
				$('#section_wrap > div.page_wrap > div.page_number a[data-pagenum=' + getPageNum() + ']').addClass('selectedPageNum');
				
				// 다음 페이지
				if (pageMakerVo.next) {
					$('#section_wrap > div.page_wrap > div.page_number').append('<a href="#none" data-pagenum="' + (pageMakerVo.endPage + 1) + '">NEX</a>');
				}
				
				// 전체 페이지
				$('#section_wrap > div.page_wrap > div.page_goto > span.page_total_num').text(addComma(pageMakerVo.totalPage.toString()));
				
			}
			
			//add_events();
			
		},
		error: function(data) {
			console.log('AJAX FAIL - ajax_getSmartPlugItems()');
		}
	});
	
}

/* function add_events() {
	console.log('add_events() CALLED!!');
	
	$('td.txt a').click(function(){
		console.log('txt CLICK EVENT HANDLER!!');
		console.log($(this));
		
		showDetail($(this).data('d_no'));
		
	});
	
	$('td.thumnail a').click(function(){
		console.log('thumnail CLICK EVENT HANDLER!!');
		console.log($(this));
		
		showDetail($(this).data('d_no'));
		
	});
	
	 $('td.modify_delete a.modify').click(function(){
		console.log('modify CLICK EVENT HANDLER!!');
		console.log($(this));
		
		location.href = '<c:url value="/user/smartPlug/smartPlug_modify?d_no="/>' + $(this).data('d_no');
		
	});
	
	$('td.modify_delete a.delete').click(function(){
		console.log('delete CLICK EVENT HANDLER!!');
		console.log($(this));
		
		let word = '정말 일기(' + $(this).data('d_txt') + ')를 삭제 하시겠습니까?';
		let result = confirm(word);
		if (result)
			ajax_deleteDiaryItem($(this).data('d_no'));
		
	});
	
	$('#smartPlug_detail_wrap div.close_download a.close').click(function() {
		console.log('close CLICK HANDLER!!');
		
		closeDetail();
		
	});
	
	$('#smartPlug_detail_wrap div.close_download a.download').click(function() {
		console.log('download CLICK HANDLER!!');
		
		console.log($(this));
		
		location.href="<c:url value='/user/smartPlug/download_img?t_id='/>" + $(this).data('t_id') + '&d_img_name=' + $(this).data('d_img_name');
		
	});
	
} 
function ajax_deleteSmartPlugItem(no) {
	console.log('ajax_deleteSmartPlugItem CALLED!!');
	
	var msg = {
		'd_no': no
	};
	$.ajax({
		url: '<c:url value="/user/smartPlug/deleteSmartPlugItem" />', 
		type: 'POST', 
		data: JSON.stringify(msg), 
		contentType: 'application/json; charset=utf-8;', 
		dataType: 'json',  
		success: function(data) {
			console.log('AJAX SUCCESS - ajax_deleteSmartPlugItem()');
			
			if (data.result > 0)
				ajax_getSmartPlugItems();
			
		},
		error: function(data) {
			console.log('AJAX ERROR - ajax_deleteSmartPlugItem()');
			
		}
	});
	 
}

function setDocumentHeight() {
	console.log('setDocumentHeight() CALLED!!');
	
	let doc_height = $(document).height();
	$('#smartPlug_detail_wrap').css('height', doc_height);
	
}

function closeDetail() {
	console.log('closeDetail() CALLED!!');
	
	$('#smartPlug_detail_wrap').css('display', 'none');
	
}

function showDetail(no) {
	console.log('showDetail() CALLED!!');
	
	$('#smartPlug_detail_wrap').css('display', 'block');
	
	ajax_getSmartPlugItem(no);
	
}

function ajax_getSmartPlugItem(no) {
	console.log('ajax_getSmartPlugItem() CALLED!!');
	
	var msg = {
		'd_no': no
	};
	$.ajax({
		url: '<c:url value="/user/smartPlug/getSmartPlugItem" />', 
		type: 'POST', 
		data: JSON.stringify(msg), 
		contentType: 'application/json; charset=utf-8;', 
		dataType: 'json',  
		success: function(data) {
			console.log('AJAX SUCCESS - ajax_getSmartPlugItem()');
			
			let userSmartPlugVo = data.userSmarPlugVo;
			let d_no = userSmartPlugVo.d_no;
			let t_id = userSmartPlugVo.t_id;
			let d_status = userSmartPlugVo.d_status;
			let d_reg_date = userSmartPlugVo.d_reg_date;
			let d_mod_date = userSmartPlugVo.d_mod_date;
			
			// UI 
			$('#smartPlug_detail_wrap span.date').text(d_write_full_year + '년 ' + d_write_month + '월 ' + d_write_date + '일(' + days_kr[d_write_day] + '요일)');
			$('#smartPlug_detail_wrap span.time').text(d_write_hours + '시 ' + d_write_minutes + '분 ' + d_write_seconds + '초');
			$('#smartPlug_detail_wrap div.write_img img').attr('src', '<c:url value="/smartPlugUploadImg/"/>' + t_id + '/' + d_img_name);
			$('#smartPlug_detail_wrap div.write_txt span').text(d_txt);
			
			// DATA
			$('#smartPlug_detail_wrap a.download').data('t_id', t_id);
			$('#smartPlug_detail_wrap a.download').data('d_img_name', d_img_name);
			
		},
		error: function(data) {
			console.log('AJAX ERROR - ajax_getsmartPlugItem()');
			
		}
	});
	
}
*/



function addComma(value) {
	console.log('addComma CALLED!!');
	
	if (value == null)
		return null;
	
	return value.replace(/\B(?=(\d{3})+(?!\d))/g, ",");
}

</script>