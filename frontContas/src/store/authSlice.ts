import type { PayloadAction } from "@reduxjs/toolkit"
import { createSlice } from "@reduxjs/toolkit"

interface Usuario{
    email: string,
    nome: string,
}

interface AuthState{
    isAuthenticated: boolean,
    usuario: Usuario | null,
    token: string | null
}

const estadoInicial: AuthState = {
   isAuthenticated: false,
   usuario: null,
   token: null 
}

const AuthSlice = createSlice({
    name: 'auth',
    initialState: estadoInicial,
    reducers: {
        loginSucesso : (state, action: PayloadAction<{usuario: Usuario, token:
            string}>)=>{
                state.isAuthenticated = true;
                state.token = action.payload.token;
                state.usuario = action.payload.usuario;
            
        },
        logout:(state)=>{
            state=estadoInicial;
            sessionStorage.removeItem('persist:root');
        }
    }
})

export const {loginSucesso,logout} = AuthSlice.actions;
export default AuthSlice.reducer;
