

export async function readSecret(code: string) {
    try {
        const url = `http://localhost:8080/api/secret/code/${code}`

        const response = await fetch(url, {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json'
            }
        })

        return response.json();
    } catch(exception) {
        throw new Error("Error fetching secret.")
    }
}

export async function createSecret(message: string) {
    const url = `http://localhost:8080/api/secret/create`
    

    const response = await fetch(url, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({message})
    })

    return response.json();
}

export default function SecretApi() {
  return (
    <div>SecretApi</div>
  )
}
