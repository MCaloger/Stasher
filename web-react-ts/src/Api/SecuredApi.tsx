export async function readSecured(code: string, password: string) {
    try {
        const url = `http://localhost:8080/api/secured/code/${code}`

        const response = await fetch(url, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({ password })
        })

        return response.json();
    } catch(exception) {
        throw new Error("Error fetching secret.")
    }
}

export async function createSecured(message: string, password: string) {

        try {
        const url = `http://localhost:8080/api/secured/create`

        const response = await fetch(url, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({ message, password })
        })

        const data = await response.json()
        return data

        } catch(exception) {
            throw new Error("Error");
        }
    
}
