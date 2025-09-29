import { useState } from "react";
import { useNavigate } from "react-router-dom";
import axios from "axios";

interface Conta {
  titulo: string;
  descricao: string;
  valor: number;
  dataVencimento: string;
  statusConta: string;
}

function ContasCriar() {
  const navigate = useNavigate();
  const [conta, setConta] = useState<Conta>({
    titulo: "",
    descricao: "",
    valor: 0,
    dataVencimento: "",
    statusConta: "PENDENTE",
  });

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    await axios.post("http://localhost:8080/contas", conta);
    navigate("/contas");
  };

  return (
    <form onSubmit={handleSubmit}>
      <div className="mb-2">
        <label>Título:</label>
        <input
          type="text"
          value={conta.titulo}
          onChange={(e) => setConta({ ...conta, titulo: e.target.value })}
          className="form-control"
          required
        />
      </div>

      <div className="mb-2">
        <label>Descrição:</label>
        <input
          type="text"
          value={conta.descricao}
          onChange={(e) => setConta({ ...conta, descricao: e.target.value })}
          className="form-control"
          required
        />
      </div>

      <div className="mb-2">
        <label>Valor:</label>
        <input
          type="number"
          value={conta.valor}
          onChange={(e) => setConta({ ...conta, valor: Number(e.target.value) })}
          className="form-control"
          required
        />
      </div>

      <div className="mb-2">
        <label>Data de Vencimento:</label>
        <input
          type="date"
          value={conta.dataVencimento || ""}
          onChange={(e) => setConta({ ...conta, dataVencimento: e.target.value })}
          className="form-control"
          required
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
        Criar
      </button>
      <button
        type="button"
        className="btn btn-secondary"
        onClick={() => navigate("/contas")}       
      >
        Cancelar
      </button>
    </form>
  );
}

export default ContasCriar;
