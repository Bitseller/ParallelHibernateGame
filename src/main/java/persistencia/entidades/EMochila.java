package persistencia.entidades;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity(name="mochila")
@Table (name="mochila")
public class EMochila {
	@Id
	@Column (name="idPersonaje")
	private int id;

    @MapsId
    @OneToOne
    @JoinColumn(name="idPersonaje")
	private EPersonaje personaje;

    @ManyToOne
    @JoinColumn(name = "item1")
    private EItem item1;

    @ManyToOne
    @JoinColumn(name = "item2")
    private EItem item2;

    @ManyToOne
    @JoinColumn(name = "item3")
    private EItem item3;

    @ManyToOne
    @JoinColumn(name = "item4")
    private EItem item4;

    @ManyToOne
    @JoinColumn(name = "item5")
    private EItem item5;

    @ManyToOne
    @JoinColumn(name = "item6")
    private EItem item6;

    @ManyToOne
    @JoinColumn(name = "item7")
    private EItem item7;

    @ManyToOne
    @JoinColumn(name = "item8")
    private EItem item8;

    @ManyToOne
    @JoinColumn(name = "item9")
    private EItem item9;
	
    @ManyToOne
    @JoinColumn(name = "item10")
    private EItem item10;
    
    @ManyToOne
    @JoinColumn(name = "item11")
    private EItem item11;
    
    @ManyToOne
    @JoinColumn(name = "item12")
    private EItem item12;
    
    @ManyToOne
    @JoinColumn(name = "item13")
    private EItem item13;
    
    @ManyToOne
    @JoinColumn(name = "item14")
    private EItem item14;
    
    @ManyToOne
    @JoinColumn(name = "item15")
    private EItem item15;
    
    @ManyToOne
    @JoinColumn(name = "item16")
    private EItem item16;
    
    @ManyToOne
    @JoinColumn(name = "item17")
    private EItem item17;
    
    @ManyToOne
    @JoinColumn(name = "item18")
    private EItem item18;
    
    @ManyToOne
    @JoinColumn(name = "item19")
    private EItem item19;

    @ManyToOne
    @JoinColumn(name = "item20")
    private EItem item20;
    @ManyToOne
    
    @JoinColumn(name = "item21")
    private EItem item21;
    
	public EMochila() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the personaje
	 */
	public EPersonaje getPersonaje() {
		return personaje;
	}

	/**
	 * @param personaje the personaje to set
	 */
	public void setPersonaje(EPersonaje personaje) {
		this.personaje = personaje;
	}

	/**
	 * @return the item1
	 */
	public EItem getItem1() {
		return item1;
	}

	/**
	 * @param item1 the item1 to set
	 */
	public void setItem1(EItem item1) {
		this.item1 = item1;
	}

	/**
	 * @return the item2
	 */
	public EItem getItem2() {
		return item2;
	}

	/**
	 * @param item2 the item2 to set
	 */
	public void setItem2(EItem item2) {
		this.item2 = item2;
	}

	/**
	 * @return the item3
	 */
	public EItem getItem3() {
		return item3;
	}

	/**
	 * @param item3 the item3 to set
	 */
	public void setItem3(EItem item3) {
		this.item3 = item3;
	}

	/**
	 * @return the item4
	 */
	public EItem getItem4() {
		return item4;
	}

	/**
	 * @param item4 the item4 to set
	 */
	public void setItem4(EItem item4) {
		this.item4 = item4;
	}

	/**
	 * @return the item5
	 */
	public EItem getItem5() {
		return item5;
	}

	/**
	 * @param item5 the item5 to set
	 */
	public void setItem5(EItem item5) {
		this.item5 = item5;
	}

	/**
	 * @return the item6
	 */
	public EItem getItem6() {
		return item6;
	}

	/**
	 * @param item6 the item6 to set
	 */
	public void setItem6(EItem item6) {
		this.item6 = item6;
	}

	/**
	 * @return the item7
	 */
	public EItem getItem7() {
		return item7;
	}

	/**
	 * @param item7 the item7 to set
	 */
	public void setItem7(EItem item7) {
		this.item7 = item7;
	}

	/**
	 * @return the item8
	 */
	public EItem getItem8() {
		return item8;
	}

	/**
	 * @param item8 the item8 to set
	 */
	public void setItem8(EItem item8) {
		this.item8 = item8;
	}

	/**
	 * @return the item9
	 */
	public EItem getItem9() {
		return item9;
	}

	/**
	 * @param item9 the item9 to set
	 */
	public void setItem9(EItem item9) {
		this.item9 = item9;
	}

	/**
	 * @return the item10
	 */
	public EItem getItem10() {
		return item10;
	}

	/**
	 * @param item10 the item10 to set
	 */
	public void setItem10(EItem item10) {
		this.item10 = item10;
	}

	/**
	 * @return the item11
	 */
	public EItem getItem11() {
		return item11;
	}

	/**
	 * @param item11 the item11 to set
	 */
	public void setItem11(EItem item11) {
		this.item11 = item11;
	}

	/**
	 * @return the item12
	 */
	public EItem getItem12() {
		return item12;
	}

	/**
	 * @param item12 the item12 to set
	 */
	public void setItem12(EItem item12) {
		this.item12 = item12;
	}

	/**
	 * @return the item13
	 */
	public EItem getItem13() {
		return item13;
	}

	/**
	 * @param item13 the item13 to set
	 */
	public void setItem13(EItem item13) {
		this.item13 = item13;
	}

	/**
	 * @return the item14
	 */
	public EItem getItem14() {
		return item14;
	}

	/**
	 * @param item14 the item14 to set
	 */
	public void setItem14(EItem item14) {
		this.item14 = item14;
	}

	/**
	 * @return the item15
	 */
	public EItem getItem15() {
		return item15;
	}

	/**
	 * @param item15 the item15 to set
	 */
	public void setItem15(EItem item15) {
		this.item15 = item15;
	}

	/**
	 * @return the item16
	 */
	public EItem getItem16() {
		return item16;
	}

	/**
	 * @param item16 the item16 to set
	 */
	public void setItem16(EItem item16) {
		this.item16 = item16;
	}

	/**
	 * @return the item17
	 */
	public EItem getItem17() {
		return item17;
	}

	/**
	 * @param item17 the item17 to set
	 */
	public void setItem17(EItem item17) {
		this.item17 = item17;
	}

	/**
	 * @return the item18
	 */
	public EItem getItem18() {
		return item18;
	}

	/**
	 * @param item18 the item18 to set
	 */
	public void setItem18(EItem item18) {
		this.item18 = item18;
	}

	/**
	 * @return the item19
	 */
	public EItem getItem19() {
		return item19;
	}

	/**
	 * @param item19 the item19 to set
	 */
	public void setItem19(EItem item19) {
		this.item19 = item19;
	}

	/**
	 * @return the item20
	 */
	public EItem getItem20() {
		return item20;
	}

	/**
	 * @param item20 the item20 to set
	 */
	public void setItem20(EItem item20) {
		this.item20 = item20;
	}

	/**
	 * @return the item21
	 */
	public EItem getItem21() {
		return item21;
	}

	/**
	 * @param item21 the item21 to set
	 */
	public void setItem21(EItem item21) {
		this.item21 = item21;
	}

	public void setItem(int i, int itemID) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		Method method = this.getClass().getMethod(("setItem"+itemID), int.class);
		method.invoke(this, i);
	}
	
	
	
}
