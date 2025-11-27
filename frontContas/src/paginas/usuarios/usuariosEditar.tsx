import { useParams, useNavigate } from "react-router-dom";
import { useEffect, useState } from "react";
import api from "../../services/api";

interface Usuario {
  nome: string;
  email: string;
  cpf: string;
}

function UsuariosEditar() {
  const { id } = useParams<{ id: string }>();
  const navigate = useNavigate();
  const [usuario, setUsuario] = useState<Usuario | null>(null);

  useEffect(() => {
    api.get(`usuarios/${id}`).then((res) => setUsuario(res.data));
  }, [id]);

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    await api.put(`usuarios/${id}`, usuario);
    navigate("/usuarios");
  };

  if (!usuario) return <p>Carregando...</p>;

  return (
    <form onSubmit={handleSubmit}>
      <div className="mb-2">
        <label>Título:</label>
        <input
          type="text"
          value={usuario.nome}
          onChange={(e) => setUsuario({ ...usuario, nome: e.target.value })}
          className="form-control"
        />
      </div>

      <div className="mb-2">
        <label>E-mail:</label>
        <input
          type="text"
          value={usuario.email}
          onChange={(e) => setUsuario({ ...usuario, email: e.target.value })}
          className="form-control"
        />
      </div>

      <div className="mb-2">
        <label>CPF:</label>
        <input
          type="text"
          value={usuario.cpf}
          onChange={(e) => setUsuario({ ...usuario, cpf: e.target.value })}
          className="form-control"
        />
      </div>

      <button type="submit" className="btn btn-primary me-2">
        Salvar
      </button>
      <button
        type="button"
        className="btn btn-secondary"
        onClick={() => navigate(`/usuarios`)}
      >
        Cancelar
      </button>
    </form>
  );
}

export default UsuariosEditar;
