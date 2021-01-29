var counter2 = 1;
function line() {
    counter2++;
}
function cloneElement(obj) {
    if(counter2>=36){
        alert("目标人员不得超过36个");
        return;
    }
    counter2 += 1;
    const parent = document.getElementById(obj);
    const clone = parent.cloneNode(true);
    clone.setAttribute("id","In"+counter2);
    parent.parentNode.appendChild(clone);
    if(counter2%3==0&&counter2!=36) {
        const newline1 = document.createElement("br");
        newline1.id = "br3"+counter2;
        parent.parentNode.appendChild(newline1);
        const newline2 = document.createElement("br");
        newline2.id = "br4"+counter2;
        parent.parentNode.appendChild(newline2);
    }
}
function delClone(obj) {
    const parent = document.getElementById(obj);
    const input = document.getElementById("In"+counter2);
    parent.parentNode.removeChild(input);
    if(counter2%3==0&&counter2!=36) {
        const newline1 = document.getElementById("br3"+counter2);
        parent.parentNode.removeChild(newline1);
        const newline2 = document.getElementById("br4"+counter2);
        parent.parentNode.removeChild(newline2);
    }
    counter2 -= 1;
}