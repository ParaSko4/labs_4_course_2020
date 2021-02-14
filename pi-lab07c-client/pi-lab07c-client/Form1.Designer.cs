namespace pi_lab07c_client
{
    partial class Form1
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            this.components = new System.ComponentModel.Container();
            this.dataGridView1 = new System.Windows.Forms.DataGridView();
            this.button1 = new System.Windows.Forms.Button();
            this.button2 = new System.Windows.Forms.Button();
            this.button3 = new System.Windows.Forms.Button();
            this.nameBox = new System.Windows.Forms.TextBox();
            this.numberBox = new System.Windows.Forms.TextBox();
            this.label1 = new System.Windows.Forms.Label();
            this.label2 = new System.Windows.Forms.Label();
            this.idkBackingFieldDataGridViewTextBoxColumn = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.namekBackingFieldDataGridViewTextBoxColumn = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.numberkBackingFieldDataGridViewTextBoxColumn = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.contactsBindingSource = new System.Windows.Forms.BindingSource(this.components);
            this.contactsBindingSource1 = new System.Windows.Forms.BindingSource(this.components);
            ((System.ComponentModel.ISupportInitialize)(this.dataGridView1)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.contactsBindingSource)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.contactsBindingSource1)).BeginInit();
            this.SuspendLayout();
            // 
            // dataGridView1
            // 
            this.dataGridView1.AllowUserToAddRows = false;
            this.dataGridView1.AllowUserToDeleteRows = false;
            this.dataGridView1.AutoGenerateColumns = false;
            this.dataGridView1.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.dataGridView1.Columns.AddRange(new System.Windows.Forms.DataGridViewColumn[] {
            this.idkBackingFieldDataGridViewTextBoxColumn,
            this.namekBackingFieldDataGridViewTextBoxColumn,
            this.numberkBackingFieldDataGridViewTextBoxColumn});
            this.dataGridView1.DataSource = this.contactsBindingSource;
            this.dataGridView1.Location = new System.Drawing.Point(12, 2);
            this.dataGridView1.Name = "dataGridView1";
            this.dataGridView1.RowHeadersWidth = 51;
            this.dataGridView1.RowTemplate.Height = 24;
            this.dataGridView1.Size = new System.Drawing.Size(551, 150);
            this.dataGridView1.TabIndex = 0;
            this.dataGridView1.CurrentCellChanged += new System.EventHandler(this.dataGridView1_CurrentCellChanged);
            // 
            // button1
            // 
            this.button1.Location = new System.Drawing.Point(313, 178);
            this.button1.Name = "button1";
            this.button1.Size = new System.Drawing.Size(135, 36);
            this.button1.TabIndex = 1;
            this.button1.Text = "Add new Contact";
            this.button1.UseVisualStyleBackColor = true;
            this.button1.Click += new System.EventHandler(this.button1_Click);
            // 
            // button2
            // 
            this.button2.Location = new System.Drawing.Point(133, 245);
            this.button2.Name = "button2";
            this.button2.Size = new System.Drawing.Size(142, 32);
            this.button2.TabIndex = 2;
            this.button2.Text = "Update Contact";
            this.button2.UseVisualStyleBackColor = true;
            this.button2.Click += new System.EventHandler(this.button2_Click);
            // 
            // button3
            // 
            this.button3.Location = new System.Drawing.Point(305, 245);
            this.button3.Name = "button3";
            this.button3.Size = new System.Drawing.Size(143, 32);
            this.button3.TabIndex = 3;
            this.button3.Text = "Delete Contact";
            this.button3.UseVisualStyleBackColor = true;
            this.button3.Click += new System.EventHandler(this.button3_Click);
            // 
            // nameBox
            // 
            this.nameBox.Location = new System.Drawing.Point(122, 172);
            this.nameBox.Name = "nameBox";
            this.nameBox.Size = new System.Drawing.Size(175, 22);
            this.nameBox.TabIndex = 4;
            // 
            // numberBox
            // 
            this.numberBox.Location = new System.Drawing.Point(122, 201);
            this.numberBox.Name = "numberBox";
            this.numberBox.Size = new System.Drawing.Size(175, 22);
            this.numberBox.TabIndex = 5;
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Location = new System.Drawing.Point(63, 172);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(53, 17);
            this.label1.TabIndex = 6;
            this.label1.Text = "Name :";
            // 
            // label2
            // 
            this.label2.AutoSize = true;
            this.label2.Location = new System.Drawing.Point(50, 204);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(66, 17);
            this.label2.TabIndex = 7;
            this.label2.Text = "Number :";
            // 
            // idkBackingFieldDataGridViewTextBoxColumn
            // 
            this.idkBackingFieldDataGridViewTextBoxColumn.DataPropertyName = "idk__BackingField";
            this.idkBackingFieldDataGridViewTextBoxColumn.HeaderText = "idk__BackingField";
            this.idkBackingFieldDataGridViewTextBoxColumn.MinimumWidth = 6;
            this.idkBackingFieldDataGridViewTextBoxColumn.Name = "idkBackingFieldDataGridViewTextBoxColumn";
            this.idkBackingFieldDataGridViewTextBoxColumn.Width = 125;
            // 
            // namekBackingFieldDataGridViewTextBoxColumn
            // 
            this.namekBackingFieldDataGridViewTextBoxColumn.DataPropertyName = "namek__BackingField";
            this.namekBackingFieldDataGridViewTextBoxColumn.HeaderText = "namek__BackingField";
            this.namekBackingFieldDataGridViewTextBoxColumn.MinimumWidth = 6;
            this.namekBackingFieldDataGridViewTextBoxColumn.Name = "namekBackingFieldDataGridViewTextBoxColumn";
            this.namekBackingFieldDataGridViewTextBoxColumn.Width = 125;
            // 
            // numberkBackingFieldDataGridViewTextBoxColumn
            // 
            this.numberkBackingFieldDataGridViewTextBoxColumn.DataPropertyName = "numberk__BackingField";
            this.numberkBackingFieldDataGridViewTextBoxColumn.HeaderText = "numberk__BackingField";
            this.numberkBackingFieldDataGridViewTextBoxColumn.MinimumWidth = 6;
            this.numberkBackingFieldDataGridViewTextBoxColumn.Name = "numberkBackingFieldDataGridViewTextBoxColumn";
            this.numberkBackingFieldDataGridViewTextBoxColumn.Width = 125;
            // 
            // contactsBindingSource
            // 
            this.contactsBindingSource.DataSource = typeof(pi_lab07c_client.ServiceReference.Contacts);
            // 
            // contactsBindingSource1
            // 
            this.contactsBindingSource1.DataSource = typeof(pi_lab07c_client.ServiceReference.Contacts);
            // 
            // Form1
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(8F, 16F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(570, 299);
            this.Controls.Add(this.label2);
            this.Controls.Add(this.label1);
            this.Controls.Add(this.numberBox);
            this.Controls.Add(this.nameBox);
            this.Controls.Add(this.button3);
            this.Controls.Add(this.button2);
            this.Controls.Add(this.button1);
            this.Controls.Add(this.dataGridView1);
            this.Name = "Form1";
            this.Text = "Form1";
            ((System.ComponentModel.ISupportInitialize)(this.dataGridView1)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.contactsBindingSource)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.contactsBindingSource1)).EndInit();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.DataGridView dataGridView1;
        private System.Windows.Forms.BindingSource contactsBindingSource;
        private System.Windows.Forms.DataGridViewTextBoxColumn idkBackingFieldDataGridViewTextBoxColumn;
        private System.Windows.Forms.DataGridViewTextBoxColumn namekBackingFieldDataGridViewTextBoxColumn;
        private System.Windows.Forms.DataGridViewTextBoxColumn numberkBackingFieldDataGridViewTextBoxColumn;
        private System.Windows.Forms.Button button1;
        private System.Windows.Forms.Button button2;
        private System.Windows.Forms.Button button3;
        private System.Windows.Forms.BindingSource contactsBindingSource1;
        private System.Windows.Forms.TextBox nameBox;
        private System.Windows.Forms.TextBox numberBox;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.Label label2;
    }
}

