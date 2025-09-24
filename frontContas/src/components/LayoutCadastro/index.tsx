function Register() {
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
        Já tem conta? <a href="/auth/login">Entre aqui</a>
      </p>
    </form>
  );
}

export default Register;
