import { useEffect, useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import axios from "axios";
import { buscarTodasContas, deletarConta, type Conta } from "../../services/contaService";

function Contas() {

  const [contas, setContas] = useState<Conta[]>([]);
  const navigate = useNavigate();

  useEffect(() => {

      const carregarContas = async () => {
          const contas = await buscarTodasContas();
          setContas(contas);
      };

      carregarContas();

  }, []);

  const contasOrdenadas = [...contas].sort((a, b) => {
    return new Date(a.dataVencimento).getTime() - new Date(b.dataVencimento).getTime();
  });

  const handleDelete = async (id: number) => {
    const confirmar = window.confirm("Tem certeza que deseja excluir esta conta?");
    if (!confirmar) return; 

    try {
        await deletarConta(id);
        setContas(contas.filter((c) => c.id !== id));
        window.alert("Conta deletada.");

    } catch (error) {
        console.error("Erro ao deletar conta:", error);
    }
  };

  return (
    <div className="container mt-4">
      <h2>Lista de Contas</h2>

      {/*  BOTAO CRIAR 
      <div className="mb-3">
        <Link to="/contas/criar" className="btn btn-success">
          Criar Conta
        </Link>
      </div> */}

      <table className="table table-striped">
        <thead>
          <tr>
            <th>Título</th>
            <th>Descrição</th>
            <th>Valor</th>
            <th>Data de Vencimento</th>
            <th>Status</th>
            <th></th>
          </tr>
        </thead>
        <tbody>
          {contasOrdenadas.length === 0 ? (
            <tr>
              <td colSpan={6} className="text-center">Nenhuma conta encontrada</td>
            </tr>
          ) : (
            contasOrdenadas.map((c) => {
              const hoje = new Date();
              const vencimento = new Date(c.dataVencimento);
              const diffMs = vencimento.getTime() - hoje.getTime();
              const diffDias = diffMs / (1000 * 60 * 60 * 24);

              let rowClass = "";
              if (diffDias < 1) rowClass = "table-danger";
              else if (diffDias < 7) rowClass = "table-warning";
              if (diffDias < 0) rowClass = "table-light";

              return (
                <tr key={c.id} className={rowClass}>
                  <td>{c.titulo}</td>
                  <td>{c.descricao}</td>
                  <td>{Number(c.valor).toFixed(2)}</td>
                  <td>{new Date(c.dataVencimento).toLocaleDateString("pt-BR")}</td>
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
              );
            })
          )}
        </tbody>
      </table>
    </div>
  )
}
  export default Contas;