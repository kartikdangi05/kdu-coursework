const btn = document.getElementById("btn");
const content = document.getElementById("display");

btn.addEventListener("click", () => {
    let input = document.getElementById("content").value;

    if(input.length == 0){
        alert("Enter valid content");
        return;
    }
    let div = document.createElement("div");
    let listEle = document.createElement("p")
    let deleteBtn = document.createElement("button");
    let editButton = document.createElement("button");
    deleteBtn.textContent = "Delete";
    editButton.textContent = "Edit";

    deleteBtn.addEventListener("click", function() {
        content.removeChild(this.parentNode);
    })

    editButton.addEventListener("click", function() {
        let editField = document.createElement("input");
        let saveButton = document.createElement("button");
        saveButton.textContent = "Save";
        div.appendChild(editField);
        div.appendChild(saveButton);
        editField.value = listEle.textContent;

        saveButton.addEventListener("click", function() {
            let editedVal = editField.value;
            listEle.textContent = editedVal;
            div.removeChild(editField);
            div.removeChild(saveButton);
        })

    })

    listEle.textContent = input
    div.appendChild(listEle)
    div.appendChild(deleteBtn);
    div.appendChild(editButton);
    content.appendChild(div);
})
