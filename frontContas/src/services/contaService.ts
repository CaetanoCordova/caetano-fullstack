import api from "./api";

interface Conta {
  titulo: string;
  descricao: string;
  valor: number;
  dataVencimento: string;
  statusConta: string;
}

//APENAS UM TESTE. AINDA NÃO USADO.

export async function buscarTodasContas():Promise<Conta[]> {
    const response = await api.get<Conta[]>("/contas");
    return response.data;
}