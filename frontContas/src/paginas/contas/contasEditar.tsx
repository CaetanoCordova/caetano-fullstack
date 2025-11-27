import { useParams, useNavigate } from "react-router-dom";
import { useEffect, useState } from "react";
import api from "../../services/api";

interface Conta {
  id: number;
  titulo: string;
  descricao: string;
  valor: number;
  dataVencimento: string;
  statusConta: string;
}

function ContasEditar() {
  const { id } = useParams<{ id: string }>();
  const navigate = useNavigate();
  const [conta, setConta] = useState<Conta | null>(null);

  useEffect(() => {
    api.get(`contas/${id}`).then((res) => setConta(res.data));
  }, [id]);

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    await api.put(`contas/${id}`, conta);
    navigate("/contas"); // volta pra lista
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
        </select>
      </div>

      <button type="submit" className="btn btn-primary me-2">
        Salvar
      </button>
      <button
        className="btn btn-primary btn-sm me-2"
        onClick={() => navigate(`/contas`)}
      >
        Cancelar
      </button>
    </form>
  );
}

export default ContasEditar;
