import { useEffect, useState } from "react";
import axios from "axios";

interface Conta {
  id: number;
  titulo: string;
  descricao: string;
  valor: number;
  dataVencimento: string;
  statusConta: string;
}

interface ContaEditarProps {
  id: number; // ID da conta que vai ser editada
  setPagina: (pagina: { nome: string; id?: number }) => void; // para voltar à tabela
}

function ContasEditar({ id, setPagina }: ContaEditarProps) {
  const [conta, setConta] = useState<Conta | null>(null);

  // Buscar os dados da conta ao abrir a tela
  useEffect(() => {
    axios
      .get(`http://localhost:8080/contas/${id}`)
      .then((res) => setConta(res.data))
      .catch((err) => console.error("Erro ao buscar conta:", err));
  }, [id]);

  // Salvar alterações
  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    if (!conta) return;

    try {
      await axios.put(`http://localhost:8080/contas/${id}`, conta);
      setPagina({ nome: "contas" }); // volta pra tabela
    } catch (err) {
      console.error("Erro ao atualizar conta:", err);
      alert("Erro ao atualizar conta");
    }
  };

  if (!conta) return <p>Carregando...</p>;

  return (
    <form onSubmit={handleSubmit}>
      <div className="mb-2">
        <label>Título:</label>
        <input
          type="text"
          value={conta.titulo}
          onChange={(e) => setConta({ ...conta, titulo: e.target.value })}
          className="form-control"
        />
      </div>

      <div className="mb-2">
        <label>Descrição:</label>
        <input
          type="text"
          value={conta.descricao}
          onChange={(e) => setConta({ ...conta, descricao: e.target.value })}
          className="form-control"
        />
      </div>

      <div className="mb-2">
        <label>Valor:</label>
        <input
          type="number"
          value={conta.valor}
          onChange={(e) => setConta({ ...conta, valor: Number(e.target.value) })}
          className="form-control"
        />
      </div>

      <div className="mb-2">
        <label>Data de Vencimento:</label>
        <input
          type="date"
          value={conta.dataVencimento.split("T")[0]}
          onChange={(e) => setConta({ ...conta, dataVencimento: e.target.value })}
          className="form-control"
        />
      </div>

      <div className="mb-2">
        <label>Status:</label>
        <select
          value={conta.statusConta}
          onChange={(e) => setConta({ ...conta, statusConta: e.target.value })}
          className="form-select"
        >
          <option value="PENDENTE">Pendente</option>
          <option value="PAGA">Paga</option>
          <option value="VENCIDA">Vencida</option>
        </select>
      </div>

      <button type="submit" className="btn btn-primary me-2">
        Salvar
      </button>
      <button
        type="button"
        className="btn btn-secondary"
        onClick={() => setPagina({ nome: "contas" })}
      >
        Cancelar
      </button>
    </form>
  );
}

export default ContasEditar;
