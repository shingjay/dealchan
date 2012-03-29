$(document).ready(function() {

	$(".deal img").fancybox({
		'transitionIn'			:	'elastic',
		'transitionOut'			:	'elastic',
		'speedIn'				:	600, 
		'speedOut'				:	200, 
		'overlayShow'			:	true,
		'content'				: 	'<img src="images/winning.jpg">',
		'enableEscapeButton'	:   true,
		'showCloseButton'		:   true,
		'title'					:   'Winning!'
	});
	
});