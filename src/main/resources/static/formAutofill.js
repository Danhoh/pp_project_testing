console.log("formAutofill");

function initFormAutoFill() {
    let rows = document.querySelectorAll("#user-table tbody tr");
    rows.forEach(row => {
        let deleteBtn = row.querySelector(".btn-danger")
        let editBtn = row.querySelector(".btn-info")

        let cols = row.querySelectorAll("td");

        let id = cols[0].innerHTML;
        let firstName = cols[1].innerHTML;
        let lastName = cols[2].innerHTML;
        let age = cols[3].innerHTML;
        let email = cols[4].innerHTML;
        let role = cols[5].innerHTML;

        let deleteForm = document.querySelector("#delete-modal form");
        let editForm = document.querySelector("#edit-modal form");

        let deleteInputs = deleteForm.querySelectorAll("input.form-control");
        let editInputs = editForm.querySelectorAll("input.form-control");

        deleteBtn.onclick = (e) => {
            deleteInputs[0].value = firstName;
            deleteInputs[1].value = lastName;
            deleteInputs[2].value = age;
            deleteInputs[3].value = email;
            deleteInputs[4].value = id;
        }

        editBtn.onclick = (e) => {
            editInputs[0].value = id;
            editInputs[1].value = firstName;
            editInputs[2].value = lastName;
            editInputs[3].value = age;
            editInputs[4].value = email;
        }

        let editModalBtn = document.querySelector("#edit-modal .btn-primary");
        let deleteModalBtn = document.querySelector("#delete-modal .btn-danger");
    })
}




