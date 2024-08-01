function del(id)  {
    $.ajax({
        type: "DELETE",
        url: "/product/book/"+id + "/delete",
        dataType: "json",
        contentType: "application/json; utf-8",
    }).done(function(){
        alert("글이 삭제되었습니다.");
        window.location.href="/product/book";
    })
}