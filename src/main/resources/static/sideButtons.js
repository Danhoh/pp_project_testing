console.log("sideButtons");

(() => {
    const SIDE_BUTTON_STYLE_ACTIVE = "btn btn-primary w-100 text-left side-button";
    const SIDE_BUTTON_STYLE_INACTIVE = "btn w-100 text-left side-button";

    let sideButtons = document.querySelectorAll(".side-button");

    sideButtons.forEach(button => {
        button.onclick = e => {
            e.preventDefault();
            sideButtons.forEach(btn => {
                btn.setAttribute("class", btn === button ? SIDE_BUTTON_STYLE_ACTIVE : SIDE_BUTTON_STYLE_INACTIVE);
            })
        }
    })
})();


