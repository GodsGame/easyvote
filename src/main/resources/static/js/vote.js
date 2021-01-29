$(function () {
    setInterval(function () {
        $("#votes").load(location.href + " #votes");//注意后面DIV的ID前面的空格，很重要！没有空格的话，会出双眼皮！（也可以使用类名）
    }, 2000);//2秒自动刷新
})