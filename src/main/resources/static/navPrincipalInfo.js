console.log("navPrincipalInfo");

(() => {
    let navUsernameElem = document.querySelector("#nav-info-username");
    let navRolesElem = document.querySelector("#nav-info-roles");

    async function loadPrincipal() {
        try {
            const response = await fetch('http://localhost:8080/api/v1/user/principal');

            if (!response.ok) {
                throw new Error('Network response was not ok');
            }

            return await response.json();
        } catch (error) {
            console.error('Fetch error:', error);

            return null;
        }
    }

    loadPrincipal().then(principal => {
        navUsernameElem.innerHTML = principal["username"];
        navRolesElem.innerHTML = principal["roles"].join(" ");
    })
})();