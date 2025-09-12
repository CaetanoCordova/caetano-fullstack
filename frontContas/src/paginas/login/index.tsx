import { Link } from "react-router-dom";

function Login() {
  return (
    <form>
      <div className="mb-3">
        <label className="form-label">E-mail</label>
        <input type="email" className="form-control" placeholder="Digite seu e-mail" />
      </div>

      <div className="mb-3">
        <label className="form-label">Senha</label>
        <input type="password" className="form-control" placeholder="Digite sua senha" />
      </div>

      <button type="submit" className="btn btn-primary w-100">Entrar</button>

      <p className="text-center mt-3">
        Não tem conta? <Link to="/cadastrese">Cadastre-se</Link>
      </p>
    </form>
  );
}

export default Login;
