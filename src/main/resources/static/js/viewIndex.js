var counter = 1;
function br(obj) {
    const target = document.getElementById(obj);
    const newline1 = document.createElement("br");
    target.appendChild(newline1);
    const newline2 = document.createElement("br");
    target.appendChild(newline2);
    counter++;
}

