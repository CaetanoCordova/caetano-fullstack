import { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import { RecuperaNovo, type RecuperaRequest} from "../../services/authService";

function Recuperacao() {
  const navigator = useNavigate();

  const [formData, setFormData] = useState<RecuperaRequest>({
    email:'',
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
      await RecuperaNovo(formData);
      console.log ("funcionou");
      navigator("/auth/codigo", { state: { email: formData.email } })
    }
  
    catch (error){
      console.error("Erro ao enviar email:", error);
    }
  }

  return (
    <form onSubmit={handleSubmit}>
      <p className="text-muted text-center">Bem-vindo! Essa é a tela de envio de código de recuperação para email</p>

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


      <button type="submit" className="btn btn-primary w-100">Enviar código</button>

      <p className="text-center mt-3">
        Voltar para a tela de <Link to="/auth/login">login</Link>
      </p>
    </form>
  );
}

export default Recuperacao;

