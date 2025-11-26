import api from "./api";

export interface LoginRequest {
    email: string,
    senha: string
}

export interface LoginResponse {
    token: string,
}

export async function LoginNovo(loginRequest : LoginRequest): Promise <LoginResponse> {
    const response = await api.post<LoginResponse>("auth/login", loginRequest)
    return response.data;
}

export interface RecuperaRequest {
    email: string
}

export interface RecuperaResponse {}

export async function RecuperaNovo(RecuperaRequest : RecuperaRequest): Promise <RecuperaResponse> {
    const response = await api.post<RecuperaResponse>("auth/enviarcodigo", RecuperaRequest)
    return response.data;
}

export interface CodigoRequest {
    email: string,
    senha: string,
    token: string
}

export interface CodigoResponse {}

export async function CodigoNovo(CodigoRequest : CodigoRequest): Promise <CodigoResponse> {
    const response = await api.post<CodigoResponse>("auth/alterarsenha", CodigoRequest)
    return response.data;
}

// const login = async(loginRequest : LoginRequest) : Promise<LoginResponse> =>{
//     const response = await api.post<LoginResponse>("auth/login", loginRequest)
//     return response.data;
// }

// const authService ={
//     login
// }