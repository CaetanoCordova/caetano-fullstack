import { useEffect, useState } from "react";
import { buscarTodosUsuarios, deletarUsuario, type Usuario } from "../../services/usuarioService";
import { useNavigate } from "react-router-dom";

function Usuarios() {
    const navigate = useNavigate();
    const [usuarios, setUsuarios] = useState<Usuario[]>([]);

    useEffect(() => {
        const carregarUsuarios = async () => {
            const usuarios = await buscarTodosUsuarios();
            setUsuarios(usuarios);
        };

        carregarUsuarios();

    }, []);
    const handleDelete = async (id: number) => {
      const confirmar = window.confirm("Tem certeza que deseja excluir este usuario?");
      if (!confirmar) return; 
  
      try {
          await deletarUsuario(id);
          setUsuarios(usuarios.filter((c) => c.id !== id));
          window.alert("Usuario deletada.");
  
      } catch (error) {
          console.error("Erro ao deletar usuario:", error);
      }
    };

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
                      className="btn btn-primary btn-sm me-2"
                    >
                      Contas
                    </button>
                    <button
                      className="btn btn-warning btn-sm me-2"
                      onClick={() => navigate(`/usuarios/${c.id}/editar`)}
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
            ))
          )}
        </tbody>
      </table>
    </div>
  );
}

export default Usuarios;
