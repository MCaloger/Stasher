import { BaseURL } from "./Url";

export async function readSecured(code: string, password: string) {
    try {
        const url = `${BaseURL}/secured/code/${code}`

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

export async function createSecured(message: string, password: string, expirationHours: number, expirationMinutes: number) {

        try {
        const url = `${BaseURL}/secured/create`

        const response = await fetch(url, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({ message, password, expirationHours, expirationMinutes })
        })

        const data = await response.json()
        return data

        } catch(exception) {
            throw new Error("Error");
        }
    
}
