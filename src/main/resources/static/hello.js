var addressbuddyload = function(){
    if($('#bookform').length){
        $('#bookform').on('submit',function(e) {
            e.preventDefault();
            $.ajax({
                    type:'POST',
                    url: "/addressbook"
            }).then(function(data) {
                   $('#noaddmsg').hide();
                   var html = $('<tr><td><p>address '+ data.id+'</p></td><td> <a href="/buddiespage/'+data.id+'">buddies</a> </td></tr>');
                   $('#books').append(html).show();

            });
        });
    }
    if ($('#buddyform').length){
        $('#buddyform').on('submit',function(e) {
        var bookid = $('#bookId').val();
        var name = $('#name').val();
        var phonenum = $('#phoneNum').val();
        var address = $('#address').val();
        e.preventDefault();
        $.ajax({
                type:'POST',
                url: "/buddy",
                datatype:'json',
                data: 'bookId='+bookid+'&name='+name+'&phonenum='+phonenum+'&address='+address,
        }).then(function(data) {
               location.reload();
        });
        });
    }

    if($('#books').length){
        $(document).on('click','#books a',function(e) {
            e.preventDefault();
            $.ajax({
                type:'GET',
                url: $(this).attr('href')
            }).then(function(data) {
                $('#lsbuddies').html($('<p>'+data+'</p>'))
            });
            return(false);
        });
    }
}
$(addressbuddyload);