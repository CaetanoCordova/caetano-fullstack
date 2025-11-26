import axios from "axios";
import { useEffect, useState } from "react";
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
  const [senhaError, setSenhaError] = useState("");

  const validatePasswords = () => {
    if (confirmarSenha && usuario.senha !== confirmarSenha) {
      setSenhaError("As senhas não coincidem");
    } else {
      setSenhaError("");
    }
  };

  useEffect(() => {
    validatePasswords();
  }, [usuario.senha, confirmarSenha]);
  
  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault(); 

    if (usuario.senha !== confirmarSenha) {
      setSenhaError("As senhas não coincidem");
      return;
    }

    try {
      const response = await axios.post("http://localhost:8080/usuarios/cadastro", usuario);
      window.alert("Cadastro realizado com êxito.");
      navigate("/auth/login");
    } catch (error: any) {
      console.error("Erro ao cadastrar:", error.response || error.message);
      window.alert("Erro ao cadastrar. Verifique os dados ou o servidor.");
    }
  };


  // MASK PRA CPF

  // const handleCpfChange = (e: React.ChangeEvent<HTMLInputElement>) => {
  //   // Remove tudo que não for número
  //   let value = e.target.value.replace(/\D/g, '');
  //   // Limita a 11 dígitos
  //   if (value.length > 11) value = value.slice(0, 11);
  //   // Aplica a máscara: ###.###.###-##
  //   value = value
  //     .replace(/(\d{3})(\d)/, '$1.$2')
  //     .replace(/(\d{3})(\d)/, '$1.$2')
  //     .replace(/(\d{3})(\d{1,2})$/, '$1-$2');
  //   setUsuario({ ...usuario, cpf: value });
  // };

  return (
    <form onSubmit={handleSubmit}>
      <p className="text-muted text-center">Bem-vindo! Essa é a tela de cadastro</p>

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
          minLength={11}
          maxLength={11}
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
          {senhaError && <p style={{ color: "red" }}>{senhaError}</p>}
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