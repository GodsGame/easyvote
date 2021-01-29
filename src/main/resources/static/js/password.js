function action1() {
    document.getElementById("form").action = "/changePassword";
    document.getElementById("form").submit();
}
function action2() {
    document.getElementById("form").action = "/sendMail";
    document.getElementById("form").submit();
}