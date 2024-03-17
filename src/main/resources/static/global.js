console.log("global");

async function sendData(url = '', method = "POST", data = {}) {
    try {
        const response = await fetch(url, {
            method: method,
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(data)
        });

        if (!response.ok) {
            let error = new Error('Network response was not ok');
            error.data = await response.json();
            throw error;
        }

        return await response.json();
    } catch (error) {
        // console.error(error.data);
        return error.data;
    }
}