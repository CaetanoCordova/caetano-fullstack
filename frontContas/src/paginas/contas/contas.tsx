import React, { useEffect, useState } from "react";
import axios from "axios";

interface Conta {
  id: number;
  titulo: string;
  descricao: string;
  valor: number;
  dataVencimento: string;
  statusConta: string;
}

interface ContasProps {
  setPagina: (pagina: { nome: string; id?: number }) => void;
}

function Contas({ setPagina }: ContasProps) {
  const [contas, setContas] = useState<Conta[]>([]);

  useEffect(() => {
    fetchContas();
  }, []);

  async function fetchContas() {
    try {
      const res = await axios.get("http://localhost:8080/contas");
      console.log("Contas recebidas:", res.data); // só pra debug
      setContas(res.data);
    } catch (err) {
      console.error(err);
      alert("Erro ao carregar contas");
    }
  }

  const handleEditar = (id: number) => setPagina({ nome: "editarConta", id });

  async function handleDelete(id: number) {
    if (!confirm("Tem certeza que deseja excluir esta conta?")) return;

    try {
      await axios.delete(`http://localhost:8080/contas/${id}`);
      setContas(contas.filter((c) => c.id !== id)); // atualiza lista local
    } catch (err) {
      console.error(err);
      alert("Erro ao deletar conta");
    }
  }


  return (
    <div className="container mt-4">
      <h2>Minhas Contas</h2>
      <table className="table table-striped">
        <thead>
          <tr>
            <th>Título</th>
            <th>Descrição</th>
            <th>Valor</th>
            <th>Vencimento</th>
            <th>Status</th>
            <th></th>
            <th></th>
          </tr>
        </thead>
        <tbody>
          {contas.length === 0 ? (
            <tr>
              <td colSpan={6} className="text-center">
                Nenhuma conta encontrada
              </td>
            </tr>
          ) : (
            contas.map((c) => (
              <tr key={c.id}>
                <td>{c.titulo}</td>
                <td>{c.descricao}</td>
                <td>{Number(c.valor).toFixed(2)}</td>
                <td>{new Date(c.dataVencimento).toLocaleDateString("pt-BR")}</td>
                <td>{c.statusConta}</td>
                <td>
                  <button
                      className="btn btn-sm btn-warning"
                      onClick={() => handleEditar(c.id)}
                    >
                      Editar
                  </button>
                </td>
                <td>
                  <button
                      className="btn btn-sm btn-danger"
                      onClick={() => handleDelete(c.id)}
                    >
                      Deletar
                  </button>
                </td>

              </tr>
            ))
          )}
        </tbody>
      </table>
    </div>
  );
}

export default Contas;
