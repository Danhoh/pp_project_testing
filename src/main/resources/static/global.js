console.log("global");

async function sendData(url = '', method="POST", data = {}){
    try {
        const response = await fetch(url, {
            method: method,
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(data)
        });

        if (!response.ok) {
            throw new Error('Network response was not ok');
        }

        const jsonResponse = await response.json();
        return jsonResponse;
    } catch (error) {
        console.error('Error:', error);
    }
}