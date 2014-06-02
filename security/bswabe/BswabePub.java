package bswabe;

import it.unisa.dia.gas.jpbc.Element;
import it.unisa.dia.gas.jpbc.Pairing;

public class BswabePub{
	/*
	 * A public key
	 */
	public String pairingDesc; // pairing description
	public Pairing p;				
	public Element g;				/* G_1 */
	public Element h;				/* G_1 */
	public Element f;				/* G_1 */
	public Element g_hat_alpha;	/* G_T */
}
