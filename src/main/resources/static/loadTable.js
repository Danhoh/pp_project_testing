console.log("loadTable");

var usersTableElem = document.querySelector("#user-table");
var principalTableElem = document.querySelector("#principal-info-table");
var usersTbodyElem = document.querySelector("#user-table tbody");
var principalTbodyElem = document.querySelector("#principal-info-table tbody");

var TABLE_CLASS_SHOWN = "table table-inner table-striped px-3";
var TABLE_CLASS_HIDDEN = "table table-inner table-striped px-3 d-none";


var tableRowElementTemplate =
    "                    <td></td>\n" +
    "                    <td></td>\n" +
    "                    <td></td>\n" +
    "                    <td></td>\n" +
    "                    <td></td>\n" +
    "                    <td></td>\n" +
    "                    <td>\n" +
    "                        <button class=\"btn btn-info\" data-target=\"#edit-modal\" data-toggle=\"modal\" type=\"button\">\n" +
    "                            Edit\n" +
    "                        </button>\n" +
    "                    </td>\n" +
    "                    <td>\n" +
    "                        <button class=\"btn btn-danger\" data-target=\"#delete-modal\" data-toggle=\"modal\" type=\"button\">\n" +
    "                            Delete\n" +
    "                        </button>\n" +
    "                    </td>";

var principleTableRowElementTemplate =
    "                    <td></td>\n" +
    "                    <td></td>\n" +
    "                    <td></td>\n" +
    "                    <td></td>\n" +
    "                    <td></td>\n" +
    "                    <td></td>\n";


async function loadUsers() {
    try {
        const response = await fetch('http://localhost:8080/api/v1/admin/user/all');

        if (!response.ok) {
            throw new Error('Network response was not ok');
        }

        return await response.json();
    } catch (error) {
        console.error('Fetch error:', error);

        return [];
    }
}

async function loadPrincipal() {
    try {
        const response = await fetch('http://localhost:8080/api/v1/user/principal');

        if (!response.ok) {
            throw new Error('Network response was not ok');
        }

        return [await response.json()];
    } catch (error) {
        console.error('Fetch error:', error);

        return [];
    }
}

function createTableRowElem(template) {
    let elem = document.createElement("tr");
    elem.innerHTML = template;

    return elem;
}

async function fillRowWithData(row, data) {
    let tData = row.querySelectorAll("td");

    tData[0].innerHTML = data["id"]; // id
    tData[1].innerHTML = data["firstName"]; // firstName
    tData[2].innerHTML = data["lastName"]; // lastName
    tData[3].innerHTML = data["age"]; // age
    tData[4].innerHTML = data["username"]; // email
    tData[5].innerHTML = data["roles"].join(" "); // role
}

async function fillTable(tbody, template, usersData) {
    tbody.innerHTML = ""; // delete all inner content in table before filling

    usersData.forEach(data => {
        let row = createTableRowElem(template);
        fillRowWithData(row, data);
        tbody.appendChild(row);
    })
}

(() => {
    // init data in both tables
    loadUsers().then(data => fillTable(usersTbodyElem, tableRowElementTemplate, data).then(() => initFormAutoFill()));
    loadPrincipal().then(data => fillTable(principalTbodyElem, principleTableRowElementTemplate, data));

    let adminSideBtn = document.querySelector("#admin-side-btn");
    let userSideBtn = document.querySelector("#user-side-btn");

    adminSideBtn.addEventListener("click", e => {
        usersTableElem.setAttribute("class", TABLE_CLASS_SHOWN);
        principalTableElem.setAttribute("class", TABLE_CLASS_HIDDEN);
    });

    userSideBtn.addEventListener("click", e => {
        document.querySelector("#user-table-btn").click();
        usersTableElem.setAttribute("class", TABLE_CLASS_HIDDEN);
        principalTableElem.setAttribute("class", TABLE_CLASS_SHOWN);

    });
})();


