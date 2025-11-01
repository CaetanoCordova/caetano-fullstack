import React, { useEffect, useState } from "react";
import { buscarTodosUsuarios, type Usuario } from "../../services/usuarioService";

function Usuario() {

    const [usuarios, setUsuarios] = useState<Usuario[]>([]);


    useEffect(() => {

        const carregarUsuarios = async () => {
            const usuarios = await buscarTodosUsuarios();
            setUsuarios(usuarios);
        };

        carregarUsuarios();

    }, []);

  return (
    <div className="container mt-4">
      <h2>Meus Usuarios</h2>
      <table className="table table-striped">
        <thead>
          <tr>
            <th>Nome</th>
            <th>E-mail</th>
            <th>CPF</th>
            <th>Permissão</th>
            <th></th>
          </tr>
        </thead>
        <tbody>
          {usuarios.length === 0 ? (
            <tr>
              <td colSpan={5} className="text-center">
                Nenhum usuario encontrado
              </td>
            </tr>
          ) : (
            usuarios.map((c) => (
              <tr key={c.id}>
                <td>{c.nome}</td>
                <td>{c.email}</td>
                <td>{c.cpf}</td>
                <td>{c.role}</td>
                <td>
                    <button
                      className="btn btn-warning btn-sm me-2"
                    >
                      Histórico
                    </button>
                    <button
                      className="btn btn-warning btn-sm"
                    >
                      Contas
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

export default Usuario;
