console.log(123);
var todayDate = new Date().getDate();

$('#available_dates').click(function () {
   $('#datetimepicker12').datetimepicker('show');
});


$('#datetimepicker12 input').datetimepicker({
	useCurrent: false,
	locale: 'it',
	format:'YYYY-MM-DD',
	minDate: new Date(new Date().setDate(todayDate + 1)),
    inline: true,
    sideBySide: true
    
}).on('dp.hide', function() {
	$(this).blur();
});

