function add_cart(id, basePath) {
    var num =  document.getElementById("number").value;
    window.location.href= basePath + '/servlet/CartServlet?gid='+id+'&num='+num+'&action=add';
}
function show_cart(id, basePath) {
    window.location.href=basePath + '/servlet/CartServlet?gid='+id+'&action=show';
}
function add() {
    var num = parseInt(document.getElementById("number").value);
    if(num < 100) {
        document.getElementById("number").value = ++num;
    }
}
function sub() {
    var num = parseInt(document.getElementById("number").value);
    if(num > 1) {
        document.getElementById("number").value = --num;
    }
}
function delcfm() {
    if (!confirm("Confirm Delete？")) {
        window.event.returnValue = false;
    }
}
