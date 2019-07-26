'use strict';

yepnope({
	test: window.jQuery && window.jQuery.fn.button && window.jQuery.fn.selectpicker,
	load: [
		'//cdnjs.cloudflare.com/ajax/libs/jquery/2.1.0/jquery.min.js',
		'//cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.1.1/js/bootstrap.min.js',
    '//cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.1.1/css/bootstrap.min.css',
		'//cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.5.4/bootstrap-select.min.js',
		'//cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.5.4/bootstrap-select.min.css'
	],
	complete: function(){
		$(".selectpicker").selectpicker();
	}
});