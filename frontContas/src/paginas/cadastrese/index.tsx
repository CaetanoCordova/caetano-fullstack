import axios from "axios";
import { useState } from "react";
import { Link, useNavigate } from "react-router-dom";

export interface Usuario {
  nome: string;
  email: string;
  cpf: string;
  senha: string;
}

function Cadastrese() {

  const navigate = useNavigate();
  const [usuario, setUsuario] = useState<Usuario>({
    nome: "",
    email: "",
    cpf: "",
    senha: "",
  });

  const [confirmarSenha, setConfirmarSenha] = useState(""); 
  
  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    try{
    await axios.post("http://localhost:8080/usuarios", usuario);

    } catch(e){

    }
    const SignupFeedback = window.alert("Cadastro realizado com êxito.");
    navigate("/auth/login");
  };

  return (
    <form onSubmit={handleSubmit}>
      <div className="mb-3">
        <label className="form-label">Nome</label>
        <input
          type="text"
          className="form-control"
          value={usuario.nome}
          onChange={(e) => setUsuario({ ...usuario, nome: e.target.value })}
          placeholder="Digite seu nome"
          required/>
      </div>

      <div className="mb-3">
        <label className="form-label">E-mail</label>
        <input
          type="email"
          className="form-control"
          value={usuario.email}
          onChange={(e) => setUsuario({ ...usuario, email: e.target.value })}
          placeholder="Digite seu e-mail"
          required/>
      </div>

      <div className="mb-3">
        <label className="form-label">CPF</label>
        <input
          type="text"
          className="form-control"
          value={usuario.cpf}
          onChange={(e) => setUsuario({ ...usuario, cpf: e.target.value })}
          placeholder="Digite seu CPF"
          maxLength={14}
          required/>
      </div>

      <div className="mb-3">
        <label className="form-label">Senha</label>
        <input
          type="password"
          className="form-control"
          value={usuario.senha}
          onChange={(e) => setUsuario({ ...usuario, senha: e.target.value })}
          placeholder="Digite sua senha"
          minLength={3}
          required/>
      </div>

      <div className="mb-3">
        <label className="form-label">Confirmar Senha</label>
        <input
          type="password"
          className="form-control"
          value={confirmarSenha}
          onChange={(e) => setConfirmarSenha(e.target.value)}
          placeholder="Confirme sua senha"
          minLength={3}
          />
      </div>

      <button type="submit" className="btn btn-success w-100">
        Cadastrar
      </button>

      <p className="text-center mt-3">
        Já tem conta? <Link to="/auth/login">Entre aqui</Link>
      </p>
    </form>
  );
}

export default Cadastrese;

{/*
import { Link } from "react-router-dom";

function Cadastrese() {
  return (
    <form>
      <div className="mb-3">
        <label className="form-label">Nome</label>
        <input type="text" className="form-control" placeholder="Digite seu nome" />
      </div>

      <div className="mb-3">
        <label className="form-label">E-mail</label>
        <input type="email" className="form-control" placeholder="Digite seu e-mail" />
      </div>

      <div className="mb-3">
        <label className="form-label">Senha</label>
        <input type="password" className="form-control" placeholder="Digite sua senha" />
      </div>

      <button type="submit" className="btn btn-success w-100">Cadastrar</button>

      <p className="text-center mt-3">
        Já tem conta? <Link to="/login">Entre aqui</Link>
      </p>
    </form>
  );
}

export default Cadastrese;
*/}