import api from "./api";

export interface Conta {
  id: number;
  titulo: string;
  descricao: string;
  valor: number;
  dataVencimento: string;
  statusConta: string;
}

export async function buscarTodasContas():Promise<Conta[]> {
    const response = await api.get<Conta[]>("/contas");
    return response.data;
}