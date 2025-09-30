import { useEffect, useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import axios from "axios";

interface Conta {
  id: number;
  titulo: string;
  descricao: string;
  valor: number;
  dataVencimento: string;
  statusConta: string;
}

function Contas() {
  const [contas, setContas] = useState<Conta[]>([]);
  const navigate = useNavigate();

  useEffect(() => {
    axios.get("http://localhost:8080/contas").then((res) => setContas(res.data));
  }, []);

  // Deletar conta
  const handleDelete = async (id: number) => {
  const confirmar = window.confirm("Tem certeza que deseja excluir esta conta?");
  if (!confirmar) return; // se cancelar, não faz nada

  await axios.delete(`http://localhost:8080/contas/${id}`);
  setContas(contas.filter((c) => c.id !== id));
};

  return (
    <div className="container mt-4">
      <h2>Lista de Contas</h2>
      <div className="mb-3">
        <Link to="/contas/criar" className="btn btn-success">
          Criar Conta
        </Link>
      </div>

      <table className="table table-striped">
        <thead>
          <tr>
            <th>ID</th>
            <th>Título</th>
            <th>Descrição</th>
            <th>Valor</th>
            <th>Data de Vencimento</th>
            <th>Status</th>
            <th>Ações</th>
          </tr>
        </thead>
        <tbody>
          {contas.map((c) => (
            <tr key={c.id}>
              <td>{c.id}</td>
              <td>{c.titulo}</td>
              <td>{c.descricao}</td>
              <td>{c.valor}</td>
              <td>{c.dataVencimento}</td>
              <td>{c.statusConta}</td>
              <td>
                <button
                  className="btn btn-primary btn-sm me-2"
                  onClick={() => navigate(`/contas/${c.id}/editar`)}
                >
                  Editar
                </button>
                <button
                  className="btn btn-danger btn-sm"
                  onClick={() => handleDelete(c.id)}
                >
                  Deletar
                </button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}

export default Contas;
