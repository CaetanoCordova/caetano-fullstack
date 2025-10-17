import axios from "axios";
import { useState } from "react";
import { useDispatch } from "react-redux";
import { Link, useNavigate } from "react-router-dom";
import { loginSucesso } from "../../store/authSlice";

interface LoginRequest {
  email: string,
  senha: string
}

interface LoginResponse {
  token: string,
}

function Login() {
  const navigator = useNavigate();

  const dispatch = useDispatch();

  const API_URL = "http://localhost:8080/"

  const [formData, setFormData] = useState<LoginRequest>({
    email:'',
    senha:''
  });

  const handleChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    const {name,value} = event.target;
    setFormData(prevState => ({
      ...prevState,
      [name]:value,
    }))

  }

  const handleSubmit = async (event: React.FormEvent<HTMLFormElement>) => {
    event.preventDefault();
    try {
      const response = await axios.post<LoginResponse>(API_URL+"auth/login",formData)

      const token = response.data.token;
      console.log(token);

      if(token!=null){
          dispatch(loginSucesso({
            usuario:{email:formData.email, nome: ""},
            token:token
        }));
      }
      navigator("/")
    }
  
    catch (error){

    }


  }

  return (
    <form onSubmit={handleSubmit}>
      <div className="mb-3">
        <label className="form-label">E-mail</label>
        <input
          type="text"
          name="email"
          id="senha"
          className="form-control"
          value={formData.email}
          onChange={handleChange}
          placeholder="Digite seu e-mail" />
      </div>

      <div className="mb-3">
        <label className="form-label">Senha</label>
        <input
          type="password"
          name="senha"
          id="senha"
          className="form-control"
          value={formData.senha}
          onChange={handleChange}
          placeholder="Digite sua senha" />
      </div>

      <button type="submit" className="btn btn-primary w-100">Entrar</button>

      <p className="text-center mt-3">
        Não tem conta? <Link to="/auth/cadastrese">Cadastre-se</Link>
      </p>
    </form>
  );
}

export default Login;
