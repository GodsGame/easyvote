var counter1 = 1;
function addElement(obj) {
    if(counter1>=56){
        alert("目标人员不得超过56个");
        return;
    }
    counter1 += 1;
    const parent = document.getElementById(obj);
    const input = document.createElement("input");
    input.className = "input";
    input.id = "input"+counter1;
    input.placeholder = "目标"+counter1;
    input.name = "target";
    parent.appendChild(input);
    if(counter1%8==0&&counter1!=56) {
        const newline1 = document.createElement("br");
        newline1.id = "br1"+counter1;
        parent.appendChild(newline1);
        const newline2 = document.createElement("br");
        newline2.id = "br2"+counter1;
        parent.appendChild(newline2);
    }
}
function delElement(obj) {
    const parent = document.getElementById(obj);
    const input  = document.getElementById("input"+counter1);
    parent.removeChild(input);
    if(counter1%8==0&&counter1!=56) {
        const newline1 = document.getElementById("br1"+counter1);
        parent.removeChild(newline1);
        const newline2 = document.getElementById("br2"+counter1);
        parent.removeChild(newline2);
    }
    counter1 -= 1;
}



