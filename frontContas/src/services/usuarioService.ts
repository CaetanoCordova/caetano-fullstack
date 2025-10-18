import api from "./api";

export interface Usuario {
  id: number;
  cpf: string;
  email: string;
  nome: number;
  senha: string;
  role: string;
}

export async function buscarTodosUsuarios():Promise<Usuario[]> {
    const response = await api.get<Usuario[]>("/usuarios");
    return response.data;
}