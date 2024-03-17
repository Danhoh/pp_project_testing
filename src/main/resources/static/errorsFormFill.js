function executeErrorsFormFill(form = null, errors = null) {
    let errorElems = form.querySelectorAll(".alert-danger");

    errorElems.forEach(errorElem => {
        errorElem.innerHTML = "";

        if (errors)
            errors.forEach(error => {
                if (errorElem.getAttribute("id").startsWith(error.field)) {
                    let className = errorElem.getAttribute("class");
                    className = className.replace("d-none", "");
                    errorElem.setAttribute("class", className);

                    errorElem.innerHTML = errorElem.innerHTML + error.defaultMessage + "<br>";
                }
            })

    })
}

function hideAllErrors(form) {
    let errorElems = document.querySelectorAll(".alert-danger");
    errorElems.forEach(elem => {
        let className = elem.getAttribute("class");
        if (!className.includes("d-none")) {
            elem.setAttribute("class", className + "d-none");
        }
    })
}

