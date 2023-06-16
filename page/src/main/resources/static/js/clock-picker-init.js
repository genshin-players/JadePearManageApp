(function($) {
    "use strict"

    // Clock pickers
/*    var input = $('#startTimeHMS').clockpicker({
        placement: 'bottom',
        align: 'left',
        autoclose: true,
        'default': 'now'
    });*/

    $('.clockpicker').clockpicker({
        donetext: 'Done',
    }).find('input').change(function () {
        console.log(this.value);
    });

    $('.check-minutes').click(function (e) {
        e.stopPropagation();
        var input = $(this).closest('.input-group').find('input');
        input.clockpicker('show').clockpicker('toggleView', 'minutes');
    });

})(jQuery)