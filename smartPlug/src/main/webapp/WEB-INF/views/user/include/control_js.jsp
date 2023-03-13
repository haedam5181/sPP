<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script type="text/javascript">

$(function() {
	
	ajax_sendData();
})

function ajax_controlSmartPlug(val) {
    console.log('controlSmartPlug called!!');
    console.log('val: ' + val);
    console.log('val type: ' + typeof(val));
    
    let msg = {
        'val'   : val
    };
    
    $.ajax({
        type: 'POST',
        url: 'http://192.168.0.5:5000/ajax_control',
        data: JSON.stringify(msg),
        dataType : 'JSON',
        contentType: 'application/json; charset=utf-8;',
        success: function(data) {
            console.log('ajax_controlSmartPlug success!!');
            console.log(data.result)
            
            if (data.result == '1')
                $('span.plug_status').text('ON');
            else
                $('span.plug_status').text('OFF');
            
        },
        error: function(data) {
            console.log('ajax_controlSmartPlug fail!!');

        }
    });
}

function ajax_sendData() {
	console.log('ajax_sendData()');
	
	$(document).on('click', '#section_wrap > div.on_off_btn > a.on_btn', function() {
		console.log('on_btn click');
		
		$.ajax({
			url: '<c:url value="/user/control/sendData" />',
			type: 'POST',
			data: {
				'data': 1
			},
			dataType : "text",
			success: function(result) {
				console.log('ajax success');
				console.log(result);
				
			},
			error: function(result) {
				console.log('ajax error')
				
			}
		})
		
	})
	
	$(document).on('click', '#section_wrap > div.on_off_btn > a.off_btn', function() {
		console.log('on_btn click');
		
		$.ajax({
			url: '<c:url value="/user/control/sendData" />',
			type: 'POST',
			data: {
				'data': 0
			},
			dataType : "text",
			success: function(result) {
				console.log('ajax success')
				console.log(result);
				
			},
			error: function(result) {
				console.log('ajax error')
				
			}
		})
		
	})
	
}

</script>