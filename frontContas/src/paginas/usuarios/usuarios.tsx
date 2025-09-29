import React, { useEffect, useState } from "react";
import axios from "axios";

interface Usuario {
  id: number;
  cpf: string;
  email: string;
  nome: number;
  senha: string;
  role: string;
}

function Home() {
  const [usuarios, setUsuarios] = useState<Usuario[]>([]);

  useEffect(() => {
    fetchUsuarios();
  }, []);

  async function fetchUsuarios() {
    try {
      const res = await axios.get("http://localhost:8080/usuarios");
      //console.log("Usuarios recebidos:", res.data);
      setUsuarios(res.data);
    } catch (err) {
      console.error(err);
      alert("Erro ao carregar usuarios");
    }
  }

  return (
    <div className="container mt-4">
      <h2>Meus Usuarios</h2>
      <table className="table table-striped">
        <thead>
          <tr>
            <th>Nome</th>
            <th>E-mail</th>
            <th>CPF</th>
            <th>Cargo</th>
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
              </tr>
            ))
          )}
        </tbody>
      </table>
    </div>
  );
}

export default Home;
